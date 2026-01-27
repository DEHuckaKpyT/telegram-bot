import type { GridSortModel } from "@mui/x-data-grid";

export const buildSortParam = (sortModel: GridSortModel): string | undefined => {
    if (!sortModel.length) return undefined;
    const { field, sort } = sortModel[0];
    return `${field},${sort}`;
};