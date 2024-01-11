import pandas as pd


def csv_to_txt(csv_path, txt_path):
    data = pd.read_csv(csv_path, encoding='utf-8')
    with open(txt_path, 'w', encoding='utf-8') as f:
        for line in data.values:
            file_name = str(line[0])
            labels = str(line[1])
            labels = "%s" % labels
            f.write(file_name + ' ' + labels + '\n')


if __name__ == '__main__':
    csv_to_txt('./train.csv', './label.txt')
