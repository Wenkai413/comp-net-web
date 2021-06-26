# 前端项目说明

## 项目启动

### 安装依赖
```
npm install
```

### 启动项目
```
npm run serve
```

## src目录结构说明
### ./store
- 放置全局变量(.js)

### ./view
- 封装独立页面(.vue)

### ./components
- 封装一些可复用的组件（如在components中封装了一个抽象的NavBar.vue的导航栏，再在view中的单个页面中实现该页面具体的导航栏，不同页面实现的NavBar.vue可能不同），(.vue)

#### ./components/common
- 封装项目级的抽象组件，一般来说可以在不同项目复用

#### ./components/content
- 封装业务级的抽象组件，一般来说只在单个项目内复用

### ./router
- 前端路由，用于不同界面间的跳转(.js)

### ./network
- 用于封装网络请求(用axios进行封装)(.js)
