package ci553.happyshop.utility;

/**
 * UIStyle is a centralized Java final class that holds all JavaFX UI-related style and size constants
 * used across all client views in the system.
 *
 * These values are grouped here rather than being hardcoded throughout the codebase:
 * - improves maintainability, ensures style consistency,
 * - avoids hardcoded values scattered across the codebase.
 *
 * Example usages:
 * - UIStyle.HistoryWinHeight for setting the height of the order history window
 * - UIStyle.labelStyle for applying consistent styling to labels
 *
 * Design rationale:
 * - Declared as a final class: prevents inheritance and misuse
 * - Private constructor: prevents instantiation (this is a static-only utility class)
 * - Holds only static constants: ensures minimal memory usage and clean syntax
 *
 * Why a Record is NOT appropriate:
 * - Records are intended for immutable instance data (e.g., DTOs), not static constants
 * - This class has no record components — everything is static
 * - We're using this as a utility container, not a data model
 *
 * Reminder:
 * Just because a class has no behavior and only data does NOT mean it should be a record.
 * If all members are static constants, use a final utility class like this one.
 */

public final class UIStyle {

    //private constructor prevents instantiation
    private UIStyle() {
        throw new UnsupportedOperationException("UIStyle is a utility class");
    }

    public static final int customerWinWidth = 610;
    public static final int customerWinHeight = 300;
    public static final int removeProNotifierWinWidth = customerWinWidth / 2 + 160;
    public static final int removeProNotifierWinHeight = 230;

    public static final int pickerWinWidth = 310;
    public static final int pickerWinHeight = 300;

    public static final int trackerWinWidth = 210;
    public static final int trackerWinHeight = 300;

    public static final int warehouseWinWidth = 630;
    public static final int warehouseWinHeight = 300;
    public static final int AlertSimWinWidth = 300;
    public static final int AlertSimWinHeight = 170;
    public static final int HistoryWinWidth = 300;
    public static final int HistoryWinHeight = 140;

    public static final int EmergencyExitWinWidth = 200;
    public static final int EmergencyExitWinHeight = 200;

    // === new style for a fresher look ===

    public static final String labelTitleStyle = "-fx-font-weight: bold; " +
            "-fx-font-size: 16px; -fx-text-fill: #4A206A;";


    public static final String labelStyle = "-fx-font-weight: bold; " +
            "-fx-font-size: 14px; " +
            "-fx-text-fill: darkblue; " +
            "-fx-background-color: #EAEAEA;"; // Very Light Gray

    public static final String labelLowStockStyle =
            "-fx-font-size: 12px; -fx-text-fill: red;";

    public static final String comboBoxStyle = "-fx-font-weight: bold; " +
            "-fx-font-size: 14px;";

    // Changed: Buttons are now a modern Teal
    public static final String buttonStyle = "-fx-font-size: 15; -fx-background-color: #008080; -fx-text-fill: white;"; // Teal

    // Changed: from 'lightgreen' to a clean, very light soft blue/gray
    public static final String rootStyle = "-fx-padding: 8px; " +
            "-fx-background-color: #F0F4F7;"; // Very Light Blue/Gray (Main App BG)

    // Changed: from 'lightblue' to a muted light blue/gray
    public static final String rootStyleBlue = "-fx-padding: 8px; " +
            "-fx-background-color: #E0E8F0;"; // Muted Light Blue (Used for Picker Detail)

    public static final String rootStyleGray = "-fx-padding: 8px; " +
            "-fx-background-color: lightgray";

    // Changed: from 'lightpink' to a very light, soft pink/beige
    public static final String rootStyleWarehouse = "-fx-padding: 8px; " +
            "-fx-background-color: #F7EBEB;"; // Very Light Pink/Beige (Muted Warehouse BG)

    // Changed: from 'lightyellow' to a soft gold/beige for less visual aggression, but still noticeable for a receipt
    public static final String rootStyleYellow = "-fx-padding: 8px; " +
            "-fx-background-color: #FFFACD;"; // Lemon Chiffon/Soft Yellow (Used for Receipt/Order Map)

    public static final String rootVipCustomerStyle = "-fx-padding: 8px; " +
            "-fx-background-color: burlywood";

    public static final String spinnerArrowStyle = "-fx-font-size: 12px; -fx-padding: 0;";

    // Changed: text fields now have a white background for better contrast
    public static final String textFiledStyle = "-fx-font-size: 16; -fx-background-color: white;";
    public static final String smallTextFiledStyle = "-fx-font-size: 14";
    public static final String tinyTextFiledStyle = "-fx-font-size: 12";

    // Changed: from 'lightpink' to a softer light blue background for product info
    public static final String labelMulLineStyle = "-fx-font-size: 16px; " +
            "-fx-background-color: #ADD8E6;"; // Light Blue

    public static final String labelPriceStyle = "-fx-font-size: 16px; " +
            "-fx-background-color: lightyellow";

    public static final String listViewStyle = "-fx-border-color: #ccc; " +
            "-fx-border-width: 1px; -fx-background-color: white; -fx-font-size: 14px;";

    public static final String manageStockChildStyle = "-fx-background-color: lightgrey; " +
            "-fx-border-color: lightgrey; " +
            "-fx-border-width: 1px; " +
            "-fx-padding: 5px;";

    public static final String manageStockChildStyle1 = "-fx-background-color: lightyellow; " +
            "-fx-border-color: lightyellow; " +
            "-fx-border-width: 1px; " +
            "-fx-padding: 5px;";

    public static final String greenFillBtnStyle = "-fx-background-color: green; " +
            "-fx-text-fill: white; -fx-font-size: 14px;";
    public static final String redFillBtnStyle = "-fx-background-color: red; " +
            "-fx-text-fill: white; -fx-font-size: 14px; ";

    public static final String searchBtnStyle = "-fx-background-color: purple; " +
            "-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;";

    public static final String grayFillBtnStyle = "-fx-background-color: gray; " +
            "-fx-text-fill: white; -fx-font-size: 14px; ";

    public static final String blueFillBtnStyle = "-fx-background-color: blue; " +
            "-fx-text-fill: white; -fx-font-size: 14px;";
    public static final String alertBtnStyle = "-fx-background-color: green; " +
            "-fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold;";

    public static final String alertTitleLabelStyle = "-fx-font-size: 16px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: red; " + "-fx-background-color: lightblue;";

    public static final String alertContentTextAreaStyle = "-fx-font-size: 14px;" +
            "-fx-font-weight: normal;-fx-control-inner-background: lightyellow; -fx-text-fill: darkblue;";

    public static final String alertContentUserActionStyle = "-fx-font-size: 14px;" +
            "-fx-font-weight: normal; -fx-text-fill: green;";

    public static final String tooltipStyle = "-fx-background-color: lightyellow; -fx-text-fill: red;";

}