import { useEffect, useState } from 'react';
import { Alert, Box } from '@mui/material';
import { DataGrid, type GridColDef, type GridPaginationModel, type GridSortModel } from '@mui/x-data-grid';
import { buildSortParam } from "../../g-shared/util/params/paramsUtil.ts";
import { useApi } from "../../a-app/provider/apiProvider.tsx";
import type { TelegramMessageListDto } from '../../g-shared/api/telegram-mesage/dto/telegramMessageListDto.ts';
import { TelegramMessagesFilters } from "../../d-widgets/telegram-message/telegramMessageFilters.tsx";

const columns: GridColDef<TelegramMessageListDto>[] = [
    { field: 'chatId', headerName: 'Chat ID', width: 150 },
    { field: 'fromId', headerName: 'Sender ID', width: 150 },
    { field: 'fromBot', headerName: 'Sender is bot', width: 150 },
    { field: 'messageId', headerName: 'Message ID', width: 150 },
    { field: 'type', headerName: 'type', width: 150 },
    { field: 'step', headerName: 'step', width: 150 },
    { field: 'stepContainerType', headerName: 'Container type', width: 150 },
    { field: 'text', headerName: 'Text', width: 150 },
    {
        field: 'createdAt',
        headerName: 'Создан',
        width: 180,
        valueGetter: v => new Date(v).toLocaleString(),
    },
];

export function TelegramMessagesPage() {
    const { telegramMessages } = useApi();
    const [ rows, setRows ] = useState<TelegramMessageListDto[]>([]);
    const [ rowCount, setRowCount ] = useState(0);
    const [ loading, setLoading ] = useState(false);
    const [ error, setError ] = useState<string | null>(null);

    const [ paginationModel, setPaginationModel ] = useState<GridPaginationModel>({
        page: 0,
        pageSize: 10,
    });

    const [ sortModel, setSortModel ] = useState<GridSortModel>([]);

    const [ chatIdsIn, setChatIdsIn ] = useState('');
    const [ fromIdsIn, setFromIdsIn ] = useState('');

    useEffect(() => {
        setLoading(true);
        setError(null);

        telegramMessages.page({
            page: paginationModel.page,
            size: paginationModel.pageSize,
            sort: buildSortParam(sortModel),

            chatIdsIn: chatIdsIn.length ? [ chatIdsIn ] : undefined,
            fromIdsIn: fromIdsIn.length ? [ fromIdsIn ] : undefined,
        })
            .then(page => {
                setRows(page.content);
                setRowCount(page.totalElements);
            })
            .catch(() => setError('Не удалось загрузить сообщения'))
            .finally(() => setLoading(false));
    }, [ paginationModel ]);

    if (error) {
        return <Alert severity="error">{error}</Alert>;
    }

    return (
        <Box>
            <TelegramMessagesFilters
                chatIdsIn={chatIdsIn}
                fromIdsIn={fromIdsIn}
                onChatIdsInChange={v => {
                    setPaginationModel(p => ({ ...p, page: 0 }));
                    setChatIdsIn(v);
                }}
                onFromIdsInChange={v => {
                    setPaginationModel(p => ({ ...p, page: 0 }));
                    setFromIdsIn(v);
                }}
                onReset={() => {
                    setChatIdsIn('');
                    setFromIdsIn('');
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