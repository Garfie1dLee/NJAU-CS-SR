import io
import json
import time

from PIL import Image
from flask import Flask, jsonify, request
from flask_cors import CORS
import torch
from torchvision import transforms

app = Flask(__name__)
CORS(app)  # 解决跨域问题
class_json_path = "./class_indices.json"
json_file = open(class_json_path, 'r')
class_indict = json.load(json_file)

model = 'models/'
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
img_size = 224


def preict_one_img(image_bytes, path):
    model_path = model + path
    net = torch.load(model_path, map_location=device)
    net.to(device=device)
    net.eval()
    my_transforms = transforms.Compose([
        transforms.Resize(img_size),
        transforms.CenterCrop(img_size),
        transforms.ToTensor(),
        transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
    ])

    image = Image.open(io.BytesIO(image_bytes))
    tensor = my_transforms(image).unsqueeze(0)

    start = time.time()
    outputs = torch.softmax(net.forward(tensor).squeeze(), dim=0)
    end = time.time()
    print(outputs)
    print(end - start)

    prediction = outputs.detach().cpu().numpy()

    template = "class:{:<15} probability:{:.3f}"
    index_pre = [(class_indict[str(index)], float(p)) for index, p in enumerate(prediction)]
    # sort probability
    index_pre.sort(key=lambda x: x[1], reverse=True)
    text = [template.format(k, v) for k, v in index_pre]
    return_info = {"result": text}
    return return_info


@app.route("/predict", methods=["POST"])
@torch.no_grad()
def predict():
    image = request.files["file"]
    path = request.form["modname"]
    img_bytes = image.read()
    info = preict_one_img(image_bytes=img_bytes, path=path)
    return jsonify(info)


@app.route("/index")
def index():
    return "今天晚上吃什么"


if __name__ == '__main__':
    app.run()
