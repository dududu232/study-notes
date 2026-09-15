@echo off
echo === 物理内存 ===
wmic memorychip get capacity
echo.
echo === 内存使用情况 ===
wmic OS get TotalVisibleMemorySize,FreePhysicalMemory,TotalVirtualMemorySize,FreeVirtualMemory /Value
echo.
echo === 任务管理器摘要 ===
tasklist /fo csv 2>nul | find /c /v ""
echo 进程总数(含标题行)
echo.
echo === Java/IDEA 相关进程内存使用 ===
wmic process where "name like '%%java%%' or name like '%%idea%%'" get Name,WorkingSetSize,ProcessId 2>nul