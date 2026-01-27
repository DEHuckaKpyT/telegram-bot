import { useEffect, useState } from 'react';
import { Box, Alert } from '@mui/material';
import { DataGrid, type GridColDef, type GridPaginationModel, type GridSortModel } from '@mui/x-data-grid';
import { getTelegramUsersPage } from "../../g-shared/api/telegram-user/telegram-user-api.ts";
import type { TelegramUserListDto } from "../../g-shared/api/telegram-user/dto/telegram-user-list-dto.ts";
import { TelegramUsersFilters } from '../../d-widgets/telegram-user/telegram-user-filters.tsx';
import { buildSortParam } from "../../g-shared/util/params/params-util.ts";

const columns: GridColDef<TelegramUserListDto>[] = [
    { field: 'userId', headerName: 'Telegram ID', width: 150 },
    { field: 'username', headerName: 'Username', width: 150 },
    { field: 'firstName', headerName: 'Имя', width: 150 },
    { field: 'lastName', headerName: 'Фамилия', width: 150 },
    { field: 'languageCode', headerName: 'Lang', width: 100 },
    {
        field: 'available',
        headerName: 'Доступен',
        width: 120,
        type: 'boolean',
    },
    {
        field: 'createdAt',
        headerName: 'Создан',
        width: 180,
        valueGetter: v => new Date(v).toLocaleString(),
    },
];

export function TelegramUsersPage() {
    const [ rows, setRows ] = useState<TelegramUserListDto[]>([]);
    const [ rowCount, setRowCount ] = useState(0);
    const [ loading, setLoading ] = useState(false);
    const [ error, setError ] = useState<string | null>(null);

    const [ paginationModel, setPaginationModel ] = useState<GridPaginationModel>({
        page: 0,
        pageSize: 10,
    });

    const [sortModel, setSortModel] = useState<GridSortModel>([]);

    const [anyStringFieldContainsIgnoreCase, setAnyStringFieldContainsIgnoreCase] = useState('');
    const [usernameContainsIgnoreCase, setUsernameContainsIgnoreCase] = useState('');
    const [userIdsIn, setUserIdsIn] = useState('');

    useEffect(() => {
        setLoading(true);
        setError(null);

        getTelegramUsersPage({
            page: paginationModel.page,
            size: paginationModel.pageSize,
            sort: buildSortParam(sortModel),

            anyStringFieldContainsIgnoreCase: anyStringFieldContainsIgnoreCase || undefined,
            usernameContainsIgnoreCase: usernameContainsIgnoreCase || undefined,
            userIdsIn: userIdsIn.length ? [userIdsIn] : undefined,
        })
            .then(page => {
                setRows(page.content);
                setRowCount(page.totalElements);
            })
            .catch(() => setError('Не удалось загрузить пользователей'))
            .finally(() => setLoading(false));
    }, [ paginationModel ]);

    if (error) {
        return <Alert severity="error">{error}</Alert>;
    }

    return (
        <Box>
            <TelegramUsersFilters
                anyStringFieldContainsIgnoreCase={anyStringFieldContainsIgnoreCase}
                usernameContainsIgnoreCase={usernameContainsIgnoreCase}
                userIdsIn={userIdsIn}
                onAnyStringFieldContainsIgnoreCaseChange={v => {
                    setPaginationModel(p => ({ ...p, page: 0 }));
                    setAnyStringFieldContainsIgnoreCase(v);
                }}
                onUsernameContainsIgnoreCaseChange={v => {
                    setPaginationModel(p => ({ ...p, page: 0 }));
                    setUsernameContainsIgnoreCase(v);
                }}
                onUserIdsInChange={v => {
                    setPaginationModel(p => ({ ...p, page: 0 }));
                    setUserIdsIn(v);
                }}
                onReset={() => {
                    setAnyStringFieldContainsIgnoreCase('');
                    setUsernameContainsIgnoreCase('');
                    setUserIdsIn('');
                    setSortModel([]);
                    setPaginationModel({ page: 0, pageSize: 10 });
                }}
            />
            <Box sx={{ height: 600 }}>
                <DataGrid
                    rows={rows}
                    columns={columns}
                    getRowId={row => row.id}
                    rowCount={rowCount}
                    loading={loading}
                    sortingMode="server"
                    onSortModelChange={setSortModel}
                    sortModel={sortModel}
                    paginationMode="server"
                    paginationModel={paginationModel}
                    onPaginationModelChange={setPaginationModel}
                    pageSizeOptions={[ 10, 25, 50 ]}
                    disableRowSelectionOnClick
                />
            </Box>
        </Box>
    );
}