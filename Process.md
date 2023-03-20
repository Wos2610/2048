1.
- Phải `setSize()` của JFrame trước khi `setLocationRelativeTo(null)` -> frame sẽ chính giữa màn hình.
- Mỗi khi muốn truy cập đến instance của Controller thì phải getInstance.
2.
- Singleton is used to ensure that a class has only one instance created throughout the application.
3.
- `addKeyListener(KeyListener l)` khi dùng cho JPanel phải có thêm `requestFocus()`

Bug
- Khi không hợp được 2 ô thì ko được sinh thêm ô mới.
- Sinh ô mới phải không được quá sát ô hợp vào.


- Nên thêm animation cho sum rồi mới addNew


Need:
- Lưu lại bàn đang chơi dở.
- Cập nhật Highest score.
- Thêm chuyển động cho các block.
- Có thể thêm thời gian đếm ngược.
