const express = require('express');
const path = require('path');
//const cors = require('cors'); // 导入 cors 模块

const app = express();

// 启用 CORS 中间件
//app.use(cors());

// 设置静态文件目录
//app.use(express.static(path.join(__dirname, 'dist')));
app.use(express.static(path.join(__dirname, 'dist'), { index: 'login.html' }));

//// 创建路由，处理根路径请求
//app.get('/', (req, res) => {
//    console.log('123');
////  res.sendFile(path.join(__dirname, 'dist/message.html'));
////    res.sendFile('login.html');
//     res.redirect('/login');
//});

// 启动服务器，监听指定端口
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
