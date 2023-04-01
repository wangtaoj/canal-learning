### 消息格式
见resources/message.json

### 消费消息入口
com.wangtao.canal.rocketmq.listener.CanalListener

### 消费逻辑
根据数据库+表名来决定由哪个processor来执行逻辑。<br />
如UserCanalProcessor，该类将canaldb数据库的user表的数据同步到es中