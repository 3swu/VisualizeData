# VisualizeData接口文档

标签（空格分隔）： 接口文档
>注：接口的返回类型为JSON

[TOC]
---

## 用户管理
url：`/account`
### 1.用户登录
- url：`/account/login`

- HTTP请求方式：POST

- 请求参数列表：
username String 用户名
password String 密码

- 响应参数：
String类型响应信息，登录成功返回"success"，失败返回"failed"。

### 2.退出登录
- url：`/account/logout`

- HTTP请求方式：GET

- 请求参数列表：
无

- 响应参数：
String类型响应信息，推出成功返回"success"，失败返回"failed"。

---
## 主界面
url：`/`
### 1.显示登录页面
- url：`/showlogin`

- HTTP请求方式：GET

- 请求参数列表：
无

- 响应参数：
返回用户登录界面

### 2.显示用户主界面
- url：`/main/{username}`

- HTTP请求方式：GET

- 路径参数：
{username} 用户名邮箱中的用户名部分（不包含'@主机地址'）

- 响应：
如果用户已经登录，则显示用户的主界面，否则抛出异常

---
## EXCEL（2007+）文件管理
url：`/file`
### 1.上传文件
- url：`/file/uploadfile`

- HTTP请求方式：POST

- 请求参数：
file： MultipartFile 用户上传的文件

- 响应信息：
如果该用户的文件列表中已有该文件，则返回"file existed"。如果上传失败，则返回"failed"。如果上传成功，则返回"success"。

### 2.查询用户的文件列表
- url：`/file/getfilelist`

- HTTP请求方式：GET

- 请求参数：无

- 响应参数：
fileID：文件的唯一ID
userID：文件拥有者的唯一ID
fileName：文件名
filePath：文件在服务器中保存的绝对路径
uploadTime：文件的上传时间（格式："yyyy-MM-dd HH:mm:ss"）

### 3.获得文件的Sheet列表
- url：`/file/getFileSheetList/{filename}/tag`

- HTTP请求方式：GET

- 请求路径参数：
{filename}：文件的全名（包含扩展名）

- 响应参数：
返回文件的Sheet列表。

### 4.获得文件的某个Sheet中具体内容
- url：`/file/getContent/{filename}/{sheetname}`

- HTTP请求方式：GET

- 请求路径参数：
{filename}：文件的全名（包含扩展名）
{sheetname}：文件中的Sheet名

- 响应参数：
返回以文件每一行（每一个元组）组成的列表，其中每个列表中的属性名为当前属性对应的文件第一行该列的名字，即该属性的属性名。返回列表中不包含文件的第一行。

---



