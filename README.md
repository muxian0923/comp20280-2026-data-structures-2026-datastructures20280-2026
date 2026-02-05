Starting repository for `Data Structures` COMP20280 2025-2026

## 运行测试 (JUnit 5)

项目已配置 Maven 和 JUnit Jupiter，可直接运行 `CircularlyLinkedListTest`。

### 在 Cursor / VS Code 中运行（推荐）

1. 确保已安装 **Extension Pack for Java** 或 **Language Support for Java**。
2. 用 Cursor 打开本项目根目录，等待右下角 Maven 导入完成。
3. 打开 `src/project20280/list/CircularlyLinkedListTest.java`，在类名或单个测试方法上方点击 **Run Test**，或使用左侧 **Testing** 视图运行。

### 使用命令行

若已安装 **Maven** 且已设置 **JAVA_HOME** 指向 JDK：

```bash
mvn test -Dtest=CircularlyLinkedListTest
```

或使用项目自带的 Maven Wrapper（需本机已设置 `JAVA_HOME` 为 JDK）：

```bash
.\mvnw.cmd test -Dtest=CircularlyLinkedListTest
```
