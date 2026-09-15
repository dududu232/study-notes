#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import uno
from urllib.parse import quote
import os
import sys
import time

def refresh_docx_toc(file_path):
    """
    打开指定路径的 docx 文件，更新其中的目录，然后保存关闭。
    返回: (成功标志, 消息)
    """
    # 文件路径检查
    if not os.path.exists(file_path):
        return False, f"文件未找到: {file_path}"

    # 转换为绝对路径
    file_path = os.path.abspath(file_path)
    file_url = f"file:///{quote(file_path.replace('\\', '/'), safe=':/')}"

    try:
        # 连接正在运行的 LibreOffice
        local_context = uno.getComponentContext()
        resolver = local_context.ServiceManager.createInstanceWithContext(
            "com.sun.star.bridge.UnoUrlResolver", local_context)
        context = resolver.resolve(
            "uno:socket,host=localhost,port=2002;urp;StarOffice.ComponentContext")
        remote_smgr = context.ServiceManager
        desktop = remote_smgr.createInstanceWithContext(
            "com.sun.star.frame.Desktop", context)

        # 打开文档
        document = desktop.loadComponentFromURL(file_url, "_blank", 0, ())
        if not document:
            return False, "文档打开失败"

        try:
            # 刷新文档
            document.refresh()

            # 获取并更新目录
            indexes = document.getDocumentIndexes()
            updated_count = 0

            if indexes.getCount() > 0:
                for i in range(indexes.getCount()):
                    toc = indexes.getByIndex(i)
                    toc.update()
                    updated_count += 1
            else:
                return False, "文档中没有找到目录"

            # 保存文档
            document.store()
            return True, f"成功更新 {updated_count} 个目录"

        finally:
            document.close(True)

    except Exception as e:
        return False, f"处理失败: {str(e)}"

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("ERROR:请指定文件路径")
        print("用法: python update_toc.py <文件路径>")
        sys.exit(1)

    file_path = sys.argv[1]
    success, message = refresh_docx_toc(file_path)

    print(message)
    sys.exit(0 if success else 1)
