import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;

public class Contents{

    private MenuItem newFile,open,save,saveAs,exit,copy,paste,selectAll,wordWrap,font;
    private MenuBar menuBar;
    public TextArea textArea;
    private Stage window;
    public Actions actions;
    private File currentFile;
    private String currentFileContents;

    public Contents(){};

    public Contents(Stage window)
    {
        this.window = window;
    }

    public String getTextArea() {
        return textArea.getText();
    }

    public void setTextArea(String text) {
        this.textArea.setText(text);
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public String getCurrentFileContents() {
        return currentFileContents;
    }

    public void setCurrentFileContents(String fileContents) {
        this.currentFileContents = fileContents;
    }

    public void setTitle(String title)
    {
        this.window.setTitle(title);
    }

    public Stage getWindow() {
        return window;
    }

    public void terminateApplication()
    {
        actions.saveUnsavedWork();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"DO U WANT TO CLOSE");
        alert.setTitle("CLOSE");
        alert.showAndWait().ifPresent(response->{
            if(response == ButtonType.OK)
                window.close();
        });
    }

    public void initializeWindow()
    {
        window.setTitle("Untitled - Notepad");
        window.setScene(initializeScene());
        window.setOnCloseRequest(e->
        {
            e.consume();
            terminateApplication();
        });
        window.show();
    }

    public Scene initializeScene()
    {
        textArea = new TextArea();
        textArea.setWrapText(false);
        textArea.setPrefColumnCount(400);
        textArea.setPrefRowCount(400);

        VBox verticalBox = new VBox();
        verticalBox.setPadding(new Insets(20,20,20,20));
        verticalBox.getChildren().addAll(menuBar, textArea);

        return new Scene(verticalBox,500,500);
    }

    public void initializeEvents()
    {
        actions = new Actions(this);
        newFile.setOnAction(e->actions.createNewFile());
        open.setOnAction(e->actions.openFile());
        save.setOnAction(e->actions.saveFile());
        saveAs.setOnAction(e->actions.saveAsFile());
        exit.setOnAction(e->actions.exitFile());
        copy.setOnAction(e->actions.copy());
        paste.setOnAction(e->actions.paste());
        selectAll.setOnAction(e->actions.selectAll());
        wordWrap.setOnAction(e->actions.wordWrap());
        font.setOnAction(e->actions.font());
    }

    public void initializeMenuBars()
    {
        Menu fileMenu,editMenu,formatMenu;
        fileMenu = new javafx.scene.control.Menu("FILE");
        editMenu = new javafx.scene.control.Menu("EDIT");
        formatMenu = new javafx.scene.control.Menu("FORMAT");

        fileMenu.getItems().addAll(newFile,open,save,saveAs,exit);
        editMenu.getItems().addAll(copy,paste,selectAll);
        formatMenu.getItems().addAll(wordWrap,font);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu,editMenu,formatMenu);
    }

    public void initializeMenuItems()
    {
        newFile = new MenuItem("New file");
        open = new MenuItem("Open");
        save = new MenuItem("Save");
        saveAs = new MenuItem("Saveas");
        exit = new MenuItem("Exit");
        copy = new MenuItem("Copy");
        paste = new MenuItem("Paste");
        selectAll = new MenuItem("Select all");
        wordWrap = new MenuItem("Word wrap");
        font = new MenuItem("Font");
    }
}
