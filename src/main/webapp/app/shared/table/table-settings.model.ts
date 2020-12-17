// This interface contains properties of table pagination settings
/**
 * @description SqTablePaginationSettingsModel is a custom type which is used in `app-table` for pagination properties
 */

export interface TablePaginationSettingsModel {
    /**
     * @description enable Pagination of rows
     */
    enablePagination: boolean;
    /**
     * @description Number of items to display on a page. By default, set to 50.
     */
    pageSize: number;
    /**
     * @description the set of provided page size options to display to the user.
     */
    pageSizeOptions: number[];
    /**
     * @description Whether to show the first/last buttons UI to the user.
     */
    showFirstLastButtons: boolean;
}
// This interface contains properties of table column settings
/**
 * @description ColumnSettingsModel is a custom type which is used in `-table` for column properties
 */
export interface ColumnSettingsModel {
    /**
     * @description Icon for the column header row
     */
    icon?: string;
    /**
     * @description The column name to be used for fetching/binding data
     */
    name: string;
    /**
     * @description The column name to be displayed
     */
    displayName: string;
    /**
     * @description Property for disabling sorting
     */
    disableSorting?: boolean;
}

