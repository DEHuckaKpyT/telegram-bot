import type { GridSortModel } from "@mui/x-data-grid";

export const buildSortParam = (sortModel: GridSortModel): string[] | undefined => {
    if (!sortModel || sortModel.length === 0) return undefined;

    return sortModel.map(item => `${item.field},${item.sort ?? 'asc'}`);
}