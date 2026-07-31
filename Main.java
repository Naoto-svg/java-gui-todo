import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Swingの画面作成はイベントディスパッチスレッドで行うのがお作法です
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java To-Do App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null); // 画面中央に配置

            // 作成したTaskPanelをウィンドウに乗せる
            TaskPanel taskPanel = new TaskPanel();
            frame.add(taskPanel);

            frame.setVisible(true);
        });
    }
}