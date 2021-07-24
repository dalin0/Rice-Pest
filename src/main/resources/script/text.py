import cv2
import numpy as np
import tensorflow as tf
import sys
import base64
from io import BytesIO
from PIL import Image
import requests
from PIL import ImageFile
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
def detection(imagePath):
    model = tf.keras.models.load_model('src/main/resources/Model/predict.h5')
    ImageFile.LOAD_TRUNCATED_IMAGES = True
    w = 200
    h = 200
    hrefs = []
    hrefs.append(imagePath)
    RANGE = 5000
    for href in hrefs:
        req  = requests.get(href,headers={'User-Agent':'Mozilla5.0(Google spider)','Range':'bytes=0-{}'.format(RANGE)})
        im = Image.open(BytesIO(req.content))
    imgs = cv2.cvtColor(np.asarray(im),cv2.COLOR_RGB2BGR)
    imgs = cv2.resize(imgs, (w,h))
    imgs = np.reshape(imgs,(1, 200, 200 ,3))
    pre = tf.cast(imgs,t'
    f.float32)
    res = model.predict(pre,batch_size = 1)
    prediction = str(np.argmax(res))
    print(prediction)
    return prediction
    # 0:水稻条纹叶枯病 1:水稻细菌性褐条病 2:水稻胡麻叶斑病
if __name__ == '__main__':
    # imagePath = 'src/main/resources/images/test2.jpg'
    imagePath = sys.argv[1]
    detection(imagePath)