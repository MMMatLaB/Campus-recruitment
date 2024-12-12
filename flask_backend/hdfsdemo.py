from flask import jsonify
from hdfs import InsecureClient

# HDFS连接信息
hdfs_url = "http://localhost:9870/"
hdfs_user = "Leonx"
hdfs_client = InsecureClient(hdfs_url, user=hdfs_user)
# 上传文件到HDFS
# local_file_path = "E:/DataGrips/test.txt"
# hdfs_target_path = "/xiaoxin/raw/test1.txt"
# #
# hdfs_client.upload(hdfs_target_path, local_file_path)
# print(f"File uploaded to  HDFS to: {hdfs_target_path}")
# print(hdfs_client.list("/xiaoxin/raw"))
# with hdfs_client.read(hdfs_target_path) as hdfs_file:
#     print(hdfs_file.read())
# with hdfs_client.read(hdfs_target_path) as hdfs_file:
#     hdfs_client.write("/tmp/test2.txt", hdfs_file, overwrite=True)
# # 下载文件从HDFS
# downloaded_file_path = "/path/to/local/downloaded_file.txt"
#
# with hdfs_client.read(hdfs_file_path) as hdfs_file, open(downloaded_file_path, 'wb') as local_file:
#     local_file.write(hdfs_file.read())
# download_path = 'downloaded_file.txt'
# hdfs_client.download(hdfs_target_path, download_path)
# with hdfs_client.read("/tmp/test.txt") as hdfs_file:
#     print(hdfs_file.read())
# # 列出 HDFS 目录内容
# hdfs_content = hdfs_client.list("/tmp")
# print("HDFS Content:", hdfs_content)
# 删除文件
# print(hdfs_client.list("/"))
# hdfs_client.upload("/tmp/test.txt", "test.txt", overwrite=True)
# print(hdfs_client.list("/tmp"))
# hdfs_client.delete("/tmp/test.txt")
# print(hdfs_client.list("/tmp"))
# hdfs_client.makedirs("/tmp/test")
# print(hdfs_client.list("/tmp"))
# hdfs_client.delete("/tmp/test")
# print(hdfs_client.list("/tmp"))
# hdfs_client.delete("/tmp/test.txt")
# # 创建目录
# hdfs_client.makedirs("/tmp/test")
# # 删除目录
# hdfs_client.delete("/tmp/test")
# # 重命名文件
# # hdfs_client.rename("/tmp/test.txt", "/tmp/test2.txt")
# # 获取文件信息
# hdfs_client.status("/tmp/test2.txt")
# # 获取文件权限
# hdfs_client.get_content_summary("/tmp/test2.txt")
def getdata(path):
    with hdfs_client.read(path,encoding='utf-8') as hdfs_file:
        return hdfs_file.read()


def uploadJson(target_path, filename):
    path='/xiaoxin/json/'+filename
    # hdfs_client.write(path,data,overwrite=True,encoding='utf-8')
    hdfs_client.upload(path,target_path,overwrite=True)
    # print(hdfs_client.list('/xiaoxin/json'))

def uploadMd(data, filename):
    path='/xiaoxin/md/'+filename+'.txt'
    hdfs_client.write(path,data,overwrite=True,encoding='utf-8')


def get_template():
    path = "/xiaoxin/excel/"
    file_name = '20xx年毕业生信息.xlsx'
    with hdfs_client.read(path+file_name,encoding='iso-8859-1') as hdfs_file:
        return hdfs_file.read()
    # return jsonify({'filename':file_name,'filecontent':file_content})


if '__main__'==__name__:
    local_file_path = "C:/Users/Leonx/Desktop/20xx年毕业生信息.xlsx"
    hdfs_target_path = "/xiaoxin/excel/20xx年毕业生信息.xlsx"
#
    hdfs_client.upload(hdfs_target_path, local_file_path)
    print(hdfs_client.list("/xiaoxin/excel"))