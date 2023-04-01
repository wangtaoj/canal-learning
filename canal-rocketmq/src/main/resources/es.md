### 创建索引
PUT /user
```json
{
  "mappings": {
    "properties": {
      "id": {
        "type": "integer",
        "index": true
      },
      "username": {
        "type": "text",
        "index": true
      }
    }
  }
}
```