[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/0mhnc0uZ)
# RESTful WebPOS
## 详细介绍
* 通过名称搜索商品
* 增减购物车中商品数量（减至0时移出购物车）
* 添加商品到购物车中
* 移出购物车种商品
* 取消购物车
* 结账
* 商品无存货时无法添加入购物车

## 运行方式
- 开启后端：
```bash
mvn clean spring-boot:run
```
- 开启前端：
    - 第一次运行：
    ```bash
    cd client
    npm install
    npm run build
    npm install express 
    node server.js
    ```
    - 后续运行：
    ```bash
    cd client
    npm run build
    node server.js
    ```

## 网页显示
http://localhost:3000/