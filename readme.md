# Habari Plugin Directory
## Purpose
This is a plugin which permit Habari to explore files and directories and serve them.

## Configuration
```json
{
  "connectionType": "com.qazima.habari.plugin.directory.Plugin",
  "configuration": {
    "defaultPages": [
      "index.htm",
      "index.html",
      "default.html"
    ],
    "errorPages": [
      {
        "404": "/var/www/errors/404.html"
      },
      {
        "500": "/var/www/errors/500.html"
      }
    ],
    "path": "/var/www/habari/",
    "uri": "(^/)(.*)"
  }
}
```
