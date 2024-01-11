import parser

import torch
from torch.optim import optimizer
import argparse
from wokr.train import getVisionTransformers_model

parser = argparse.ArgumentParser()
    # Required parameters
    #parser.add_argument("--dataset", choices=["cifar10", "cifar100"], default="cifar10",
                        #help="Which downstream task.")
parser.add_argument("--output_dir", default="../output", type=str,
                        help="The output directory where checkpoints will be written.")
parser.add_argument("--img_size", default=224, type=int,help="Resolution size")
parser.add_argument("--train_batch_size", default=16, type=int,
                        help="Total batch size for training.")
parser.add_argument("--eval_batch_size", default=16, type=int,
                        help="Total batch size for eval.")
parser.add_argument("--learning_rate", default=0.01, type=float,
                        help="The initial learning rate for SGD.")
parser.add_argument("--weight_decay", default=0, type=float,
                        help="Weight deay if we apply some.")
parser.add_argument("--total_epoch", default=1, type=int,
                        help="Total number of training epochs to perform.")


args = parser.parse_args()
device = torch.device("cuda:0")
args.device = device
args,modle=getVisionTransformers_model(args)
modle.load_state_dict(torch.load('models/model'))
modle.eval()
torch.save(modle,'models/stupid')