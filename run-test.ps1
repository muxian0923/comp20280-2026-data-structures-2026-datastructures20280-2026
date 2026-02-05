# 使用 Cursor Java 扩展自带的 JDK 21 运行测试，避免本机 Java 8 的 SSL 问题
$jdkPath = "$env:USERPROFILE\.cursor\extensions\redhat.java-1.52.0-win32-x64\jre\21.0.9-win32-x86_64"
if (-not (Test-Path "$jdkPath\bin\java.exe")) {
    $jdkPath = "$env:USERPROFILE\.vscode\extensions\redhat.java-*\jre\*\bin\java.exe" | Get-Item -ErrorAction SilentlyContinue | ForEach-Object { $_.Directory.Parent.Parent.FullName } | Select-Object -First 1
}
if ($jdkPath) {
    $env:JAVA_HOME = $jdkPath
}
& "$PSScriptRoot\mvnw.cmd" @args
