package com.example.app.game.core;

/** 
 * ゲームモードのインターフェース
 * 
 * @author takeshi.kashiwagi
 */
public interface GameMode {
    String name(); // APIの返却で、Enum.name() を利用するために明示的に定義
    String label(); // 表示名（日本語）
}
