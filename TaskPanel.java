import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TaskPanel extends JPanel {
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskListView;
    private JTextField taskInputField;

    public TaskPanel() {
        // レイアウトの設定（総枠の配置を上下に分ける）
        setLayout(new BorderLayout());

        // --- 上部：入力エリア（テキストボックス ＋ 追加ボタン） ---
        JPanel topPanel = new JPanel();
        taskInputField = new JTextField(20);
        JButton addButton = new JButton("タスク追加");

        topPanel.add(taskInputField);
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        // --- 中央：タスク一覧リストエリア ---
        taskListModel = new DefaultListModel<>();
        taskListView = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskListView);
        add(scrollPane, BorderLayout.CENTER);

        // --- 下部：操作ボタンエリア（削除ボタン ＋ 完了切り替えボタン） ---
        JPanel bottomPanel = new JPanel();
        JButton deleteButton = new JButton("選択タスクを削除");
        JButton toggleButton = new JButton("完了/未完了を切り替え");

        bottomPanel.add(deleteButton);
        bottomPanel.add(toggleButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- ボタンが押されたときの動き（イベント処理） ---
        // 1. 追加ボタン
        addButton.addActionListener((ActionEvent e) -> {
            String title = taskInputField.getText().trim();
            if (!title.isEmpty()) {
                Task newTask = new Task(title);
                taskListModel.addElement(newTask);
                taskInputField.setText(""); // 入力欄をクリア
            }
        });

        // 2. 削除ボタン
        deleteButton.addActionListener((ActionEvent e) -> {
            int selectedIndex = taskListView.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            }
        });

        // 3. 完了/未完了 切り替えボタン
        toggleButton.addActionListener((ActionEvent e) -> {
            Task selectedTask = taskListView.getSelectedValue();
            if (selectedTask != null) {
                selectedTask.setCompleted(!selectedTask.isCompleted());
                taskListView.repaint(); // リストの表示を更新
            }
        });
    }
}