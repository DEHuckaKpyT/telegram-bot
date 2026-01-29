import { useEffect, useState } from 'react';
import { Alert, Box } from '@mui/material';
import { DataGrid, type GridColDef, type GridPaginationModel, type GridSortModel } from '@mui/x-data-grid';
import type { TelegramUserListDto } from "../../g-shared/api/telegram-user/dto/telegramUserListDto.ts";
import { TelegramUsersFilters } from '../../d-widgets/telegram-user/telegramUserFilters.tsx';
import { buildSortParam } from "../../g-shared/util/params/paramsUtil.ts";
import { useApi } from "../../a-app/provider/apiProvider.tsx";

const columns: GridColDef<TelegramUserListDto>[] = [
    { field: 'userId', headerName: 'Telegram ID', width: 150 },
    { field: 'username', headerName: 'Username', width: 150 },
    { field: 'firstName', headerName: 'First name', width: 150 },
    { field: 'lastName', headerName: 'Last name', width: 150 },
    { field: 'languageCode', headerName: 'Lang code', width: 100 },
    {
        field: 'available',
        headerName: 'Available',
        width: 120,
        type: 'boolean',
    },
    {
        field: 'createdAt',
        headerName: 'Created at',
        width: 180,
        valueGetter: v => new Date(v).toLocaleString(),
    },
];

export function TelegramUsersPage() {
    const { telegramUsers } = useApi();
    const [ rows, setRows ] = useState<TelegramUserListDto[]>([]);
    const [ rowCount, setRowCount ] = useState(0);
    const [ loading, setLoading ] = useState(false);
    const [ error, setError ] = useState<string | null>(null);

    const [ paginationModel, setPaginationModel ] = useState<GridPaginationModel>({
        page: 0,
        pageSize: 10,
    });

    const [ sortModel, setSortModel ] = useState<GridSortModel>([]);

    const [ anyStringFieldContainsIgnoreCase, setAnyStringFieldContainsIgnoreCase ] = useState('');
    const [ usernameContainsIgnoreCase, setUsernameContainsIgnoreCase ] = useState('');
    const [ userIdsIn, setUserIdsIn ] = useState('');

    useEffect(() => {
        setLoading(true);
        setError(null);

        telegramUsers.page({
            page: paginationModel.page,
            size: paginationModel.pageSize,
            sort: buildSortParam(sortModel),

            anyStringFieldContainsIgnoreCase: anyStringFieldContainsIgnoreCase || undefined,
            usernameContainsIgnoreCase: usernameContainsIgnoreCase || undefined,
            userIdsIn: userIdsIn.length ? [ userIdsIn ] : undefined,
        })
            .then(page => {
                setRows(page.content);
                setRowCount(page.totalElements);
            })
            .catch(() => setError('Не удалось загрузить пользователей'))
            .finally(() => setLoading(false));
    }, [ paginationModel, sortModel ]);

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