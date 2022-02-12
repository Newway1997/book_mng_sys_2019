# Dockerfile
# 使用node做为镜像
FROM node:10.19.0
# 在容器中创建该目录
RUN mkdir -p /home/project
# 设置容器的工作目录为该目录
WORKDIR /home/project 
# 向外提供8080端口
EXPOSE 8080
# 容器创建完成后执行的命令，本来应该由服务器npm run build的，但是我的Linux配置不行，建议本地build就行，服务器nginx映射好就行
# CMD npm install --registry=https://registry.npmmirror.com && npm run build
