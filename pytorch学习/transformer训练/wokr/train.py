import ml_collections
import argparse
from modeling import VisionTransformer
from load_cifa10 import get_loader
import torch
import os
import numpy as np

def get_config():
    '''
    配置transformer的模型的参数
    '''
    config = ml_collections.ConfigDict()
    config.patches = ml_collections.ConfigDict({'size':16})
    config.hidden_size = 768
    config.transformer = ml_collections.ConfigDict()
    config.transformer.mlp_dim = 3072
    config.transformer.num_heads = 12
    config.transformer.num_layers = 12
    config.transformer.attention_dropout_rate = 0.0
    config.transformer.dropout_rate = 0.1
    config.classifier = 'token'
    config.representation_size = None
    return config


def save_model(args, model, epoch_index):
    '''
    保存每个epoch训练的模型
    '''

    model_to_save = model.module if hasattr(model, 'module') else model
    model_checkpoint = os.path.join(args.output_dir, "epoch%s_checkpoint.bin" % epoch_index)
    torch.save(model_to_save.state_dict(), 'models/model')


#实例化模型
def getVisionTransformers_model(args):
    config=get_config()#获取模型的配置文件
    num_classes = 5
    model = VisionTransformer(config, args.img_size, zero_head=True, num_classes=num_classes)
    model.to(args.device)
    return args,model


#用测试集评估模型的训练好坏
def eval(args,model,test_loader):
    eval_loss=0.0
    total_acc=0.0
    model.eval()
    loss_function = torch.nn.CrossEntropyLoss()
    for i,batch in enumerate(test_loader):
        batch = tuple(t.to(args.device) for t in batch)
        x, y = batch
        with torch.no_grad():
            logits,_= model(x)#model返回的是（bs,num_classes）和weight
            batch_loss=loss_function(logits,y)
            #记录误差
            eval_loss+=batch_loss.item()
            #记录准确率
            _,preds= logits.max(1)
            num_correct=(preds==y).sum().item()
            total_acc+=num_correct

    loss=eval_loss/len(test_loader)
    acc=total_acc/(len(test_loader)*args.eval_batch_size)
    return loss,acc





def train(args,model):
    print("load dataset.........................")
    #加载数据
    train_loader, test_loader = get_loader(args)
    # Prepare optimizer and scheduler
    optimizer = torch.optim.SGD(model.parameters(),
                                lr=args.learning_rate,
                                momentum=0.9,
                                weight_decay=args.weight_decay)

    print("training.........................")
    #设置测试损失list,和测试acc 列表
    val_loss_list=[]
    val_acc_list=[]
    #设置训练损失list
    train_loss_list=[]
    for i in range(args.total_epoch):
        model.train()
        train_loss=0
        for step, batch in enumerate(train_loader):
            batch = tuple(t.to(args.device) for t in batch)
            x, y = batch
            loss = model(x, y)
            train_loss +=loss.item()
            loss.backward()
            optimizer.step()
            optimizer.zero_grad()

        #每训练一个epoch,记录一次训练损失
        train_loss=train_loss/len(train_loader)
        train_loss_list.append(train_loss)
        np.savetxt("train_loss_list.txt", train_loss_list)
        print("train Epoch:{},loss:{}".format(i,train_loss))

        # 每个epcoh保存一次模型参数
        save_model(args, model,i)
        # 每训练一个epoch,用当前训练的模型对验证集进行测试
        eval_loss, eval_acc = eval(args, model, test_loader)
        #将每一个测试集验证的结果加入列表
        val_loss_list.append(eval_loss)
        val_acc_list.append(eval_acc)
        np.savetxt("val_loss_list.txt",val_loss_list)
        np.savetxt("val_acc_   list.txt",val_acc_list)
        print("val Epoch:{},eval_loss:{},eval_acc:{}".format(i, eval_loss, eval_acc))

def main():
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
    device = torch.device("cpu")
    args.device = device

    args,modle=getVisionTransformers_model(args)
    train(args,modle)
    torch.save(modle,'models/mmm')

if __name__ == "__main__":
    main()