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

Graphics
- Hàm `setRenderingHint(RenderingHints.Key hintKey, Object hintValue)` :  thiết lập các thuộc tính như chất lượng vẽ, hiệu ứng bóng đổ, sự mờ hoặc độ sáng của hình ảnh, tốc độ xử lý và các thuộc tính khác liên quan đến việc tối ưu hóa vẽ đồ họa.

23/03/2023
- JPanel khi `setOpaque(false)` mới hiển thị.

- TimingFramework

Đối tượng Animator: TimingFramework sử dụng đối tượng Animator để quản lý các animation. Đối tượng này có thể được cấu hình để chạy animation trong một khoảng thời gian nhất định và với các tham số điều khiển khác.

Interpolator: TimingFramework cung cấp một số lượng lớn các Interpolator khác nhau để tạo các đường cong chuyển động khác nhau. Interpolator sử dụng để kiểm soát tốc độ của animation trong quá trình thực hiện.

TimingSource: Đây là thành phần trung tâm của TimingFramework. TimingSource xác định thời gian giữa các khung hình trong animation. TimingSource có thể được cấu hình để sử dụng các nguồn thời gian khác nhau như các sự kiện hệ thống, hoặc bộ đếm thời gian chính xác.

TimingTarget: Đây là một đối tượng mà Animator được cấu hình để thay đổi các thuộc tính của nó trong quá trình thực hiện animation. TimingTarget thường được sử dụng để thay đổi các thuộc tính trên các thành phần giao diện người dùng, chẳng hạn như độ trong suốt, kích thước, vị trí và màu sắc.

PropertySetter: Lớp này được sử dụng để thiết lập các giá trị thuộc tính của các đối tượng trong quá trình thực hiện animation. PropertySetter có thể được sử dụng để thay đổi các thuộc tính của bất kỳ đối tượng nào, không chỉ các thành phần giao diện người dùng.

SplineInterpolator: Đây là một loại Interpolator đặc biệt, được sử dụng để tạo ra các đường cong chuyển động mượt mà và tự nhiên hơn. SplineInterpolator cung cấp các tham số điều khiển để tùy chỉnh đường cong chuyển động theo cách mà người dùng mong muốn.

AnimatorBuilder: Đối tượng AnimatorBuilder được sử dụng để cấu hình các thông số cho đối tượng Animator


- Animator animator = new Animator.Builder(). Beacuse Animator class, which is an abstract class and cannot be instantiated directly. Instead, you need to use the Animator.Builder to create an instance of the Animator class.

- Khởi tạo một SwingTimerTimingSource để cung cấp thời gian và một thời gian chạy của Animator.
```java
  TimingSource timingSource = new SwingTimerTimingSource();
```

```java
  Animator animator = new Animator.Builder(timingSource)
          .setDuration(durationMs, TimeUnit.MILLISECONDS)
          .setInterpolator(new SplineInterpolator(0.4, 0.0, 0.2, 1.0))
          .addTarget(PropertySetter.getTarget(panel, "size", new Dimension(0, 0), new Dimension(200, 200)))
          .build();
```

SplineInterpolator được sử dụng để tạo ra một đường cong trơn tru hơn và tự nhiên hơn trong quá trình thay đổi kích thước của JPanel.

setSize()/setPreferredSize()