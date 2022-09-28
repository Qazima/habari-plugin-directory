# Habari Plugin Directory
## Purpose
This is a plugin which permit Habari to explore files and directories and serve them.

## Configuration
```json
{
  "configurationUri": "^/config.*",
  "connectionType": "com.qazima.habari.plugin.directory.Plugin",
  "defaultPages": [
    "index.htm",
    "index.html",
    "default.html"
  ],
  "errorPages": [
    {"404": "/var/www/error/404.html"},
    {"500": "/var/www/error/500.html"}
  ],
  "path": "/home/csluikidikilest/Documents/Development/Web/",
  "metadataUri": "^/metadata.*",
  "uri": "^/(?!api/).*"
}
```
