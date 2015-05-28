package com.stantonj.chatter.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Joey on 5/27/15.
 */
public class ChattrTab extends Tab {

    public enum MoveDirection{
        RIGHT("Right"),
        DOWN("Down");
        private String DirectionName;

        public String getDirectionName() {
            return DirectionName;
        }

        MoveDirection(String direction){
            DirectionName = direction;
        }

    }

    private Map<MoveDirection, MenuItem> directionalContextMenuItems = new ConcurrentHashMap<>();

    public ChattrTab() {
        this("");
    }

    public ChattrTab(String text) {
        super(text);
        setContextMenu(new ContextMenu());
    }

    public void enableDirection(MoveDirection direction, EventHandler<ActionEvent> clickHandler){
        if(directionalContextMenuItems.containsKey(direction))
            return;
        MenuItem item = new MenuItem("Move Tab " + direction.getDirectionName());
        item.setOnAction(clickHandler);
        directionalContextMenuItems.put(direction, item);
        getContextMenu().getItems().add(item);
    }

    public void disableDirection(MoveDirection direction){
        if(!directionalContextMenuItems.containsKey(direction))
            return;
        getContextMenu().getItems().removeAll(
                directionalContextMenuItems.get(direction)
        );
        directionalContextMenuItems.remove(direction);
    }




}
