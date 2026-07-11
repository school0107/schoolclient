# SchoolClient Mod

Một Minecraft Client Mod được phát triển với Fabric Loader.

## 🎮 Tính Năng

### Animation (1.7 Style)
- Hoạt ảnh swing weapon mượt mà theo style 1.7
- Tốc độ hoạt ảnh có thể điều chỉnh
- Bật/tắt trong menu cài đặt

### Zoom
- Phóng to/thu nhỏ tầm nhìn bằng scroll wheel
- Mức zoom tối đa có thể cấu hình
- Reset zoom bằng Ctrl+C

### HUD (Heads-Up Display)
- **FPS** - Hiển thị frames per second
- **Ping** - Độ trễ mạng
- **CPS** - Clicks per second (chuột trái & phải riêng biệt)
- **Tọa độ** - Vị trí XYZ của người chơi
- **Hướng nhìn** - Chiều nhìn hiện tại (North, South, East, West)

## ⌨️ Phím Tắt

| Phím | Chức Năng |
|------|----------|
| **N** | Mở menu cài đặt |
| **Scroll Wheel** | Phóng to/thu nhỏ |
| **Ctrl+C** | Reset zoom |

## 🔧 Cài Đặt

Tất cả cài đặt được lưu trong file `schoolclient.json` trong thư mục config:
- Animation settings (enable, speed)
- Zoom settings (enable, max zoom)
- HUD settings (show FPS, ping, CPS, coordinates, direction)
- HUD position và scale

## 📋 Yêu Cầu

- Minecraft 1.20+
- Fabric Loader 0.14+
- Fabric API

## 🛠️ Biên Dịch

```bash
./gradlew build
```

File JAR sẽ nằm trong `build/libs/`

## 📝 License

MIT License
