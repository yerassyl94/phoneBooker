# Device booking app

## Available requests

* *GET* `/device/all` - returns all available devices
* *GET* `/device?id=<id>` - returns data of specific device
* *GET* `/booking/isAvailable?id=<id>` - return availability of device
* *GET* `/booking/getLast?id=<id>` - return last booking record of device
* *POST* `/booking/book` - book the device
* *POST* `/booking/return` - return the device

### Post request body

```json
{
  "id": "<UUID>",
  "user": "<Int>"
}
```

## Run

```shell
docker-compose up
```