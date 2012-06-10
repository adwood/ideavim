package com.maddyhome.idea.vim.action.change.insert;

/*
 * IdeaVim - A Vim emulator plugin for IntelliJ Idea
 * Copyright (C) 2003-2005 Rick Maddy
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.maddyhome.idea.vim.command.Argument;
import com.maddyhome.idea.vim.group.CommandGroups;
import com.maddyhome.idea.vim.handler.ChangeEditorActionHandler;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class InsertSpaceBeforeCursorAction extends EditorAction {
  public InsertSpaceBeforeCursorAction() {
    super(new Handler());
  }

  private static class Handler extends ChangeEditorActionHandler {
    public boolean execute(Editor editor, DataContext context, int count, int rawCount, Argument argument) {
      if (editor.isOneLineMode()) {
        return false;
      }

      List<KeyStroke> keys = new ArrayList<KeyStroke>();
      keys.add(KeyStroke.getKeyStroke('i'));
      keys.add(KeyStroke.getKeyStroke(' '));
      keys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
      keys.add(KeyStroke.getKeyStroke('l'));

      CommandGroups.getInstance().getMacro().playbackKeys(
              editor, context, PlatformDataKeys.PROJECT.getData(context), keys, 0, 0, 1);

      return true;
    }
  }
}
