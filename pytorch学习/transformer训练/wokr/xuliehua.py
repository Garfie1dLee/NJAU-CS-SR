import torch
import torchvision

model = torch.load('models/test', map_location=torch.device('cpu'))
model.eval()
example = torch.rand(1, 3, 224, 224)
mobile_module = torch.jit.trace(model, example)
mobile_module.save("models/model.pt")
