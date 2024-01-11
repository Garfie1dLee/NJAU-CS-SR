import torch
import torch.nn as nn
from PIL import ImageDraw, ImageFont, Image
from torchvision import datasets, transforms
from torch.utils.data import DataLoader
import os

image_transforms = {
    'pre': transforms.Compose([
        transforms.Resize(size=224),
        transforms.CenterCrop(size=224),
        transforms.ToTensor(),
        transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225]),
    ])
}
dataset = "data"
predict_directory = os.path.join(dataset, 'test')
data = {
    'predict': datasets.ImageFolder(root=predict_directory, transform=image_transforms['pre'])
}
batch_size = 32
num_classes = 5
pre_data_size = len(data['predict'])
pre_data = DataLoader(data['predict'], batch_size=batch_size, shuffle=True)
idx_to_class = {v: k for k, v in data['predict'].class_to_idx.items()}


def computeTestSetAccuracy(model, loss_function):
    device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
    test_acc = 0.0
    test_loss = 0.0

    with torch.no_grad():
        model.eval()

        for j, (inputs, labels) in enumerate(pre_data):
            inputs = inputs.to(device)
            labels = labels.to(device)

            outputs = model(inputs)

            loss = loss_function(outputs, labels)

            test_loss += loss.item() * inputs.size(0)

            ret, predictions = torch.max(outputs.data, 1)
            correct_counts = predictions.eq(labels.data.view_as(predictions))

            acc = torch.mean(correct_counts.type(torch.FloatTensor))

            test_acc += acc.item() * inputs.size(0)

            print("Test Batch Number: {:03d}, Test: Loss: {:.4f}, Accuracy: {:.4f}".format(
                j, loss.item(), acc.item()
            ))
    avg_test_loss = test_loss / pre_data_size
    avg_test_acc = test_acc / pre_data_size
    print("Test accuracy : " + str(avg_test_acc))


def predict(model, test_image_name):
    transform = image_transforms['pre']

    test_image = Image.open(test_image_name)

    draw = ImageDraw.Draw(test_image)

    test_image_tensor = transform(test_image)

    if torch.cuda.is_available():
        test_image_tensor = test_image_tensor.view(1, 3, 224, 224).cuda()
    else:
        test_image_tensor = test_image_tensor.view(1, 3, 224, 224)

    with torch.no_grad():
        model.eval()

        out = model(test_image_tensor)
        ps = torch.exp(out)
        topk, topclass = ps.topk(1, dim=1)
        print("Prediction : ", idx_to_class[topclass.cpu().numpy()[0][0]], ", Score: ", topk.cpu().numpy()[0][0])
        text = idx_to_class[topclass.cpu().numpy()[0][0]] + " " + str(topk.cpu().numpy()[0][0])
        font = ImageFont.truetype('arial.ttf', 36)
        draw.text((0, 0), text, (255, 0, 0), font=font)
        test_image.show()


device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
model = torch.load('models/h7', map_location=torch.device('cpu'))
loss_func = nn.NLLLoss()

predict(model, 'predict/3403726678.jpg')