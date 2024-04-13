[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/0mhnc0uZ)
# RESTful WebPOS
请参考[spring-petclinic/spring-petclinic-rest](https://github.com/spring-petclinic/spring-petclinic-rest) 将aw03的webpos项目改为REST架构风格的应用系统。具体要求包括：

- 请使用OpenAPI的定义REST接口，参考[api.yml](src/main/resources/api.yml)；
- 请`client`目录下给大家提供的客户端与你所写的RESTfulWebPOS接起来
  - 当前`client`目录下的客户端可以与一个[假的后端服务](https://github.com/typicode/json-server)对接，具体请看[client/README.md](client/README.md)
- 请编写README.md，详细介绍你所写的RESTfulWebPOS;
- 请着重理解**应用状态**与**资源状态**两个概念，特别是如何实现客户端与服务器的无状态交互，并代码实现中予以体现，并README.md中写下你的理解。