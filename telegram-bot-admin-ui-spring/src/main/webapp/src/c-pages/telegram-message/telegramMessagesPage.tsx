import { useEffect, useState } from 'react';
import { Alert, Box } from '@mui/material';
import { DataGrid, type GridColDef, type GridPaginationModel, type GridSortModel } from '@mui/x-data-grid';
import { buildSortParam } from "../../g-shared/util/params/paramsUtil.ts";
import { useApi } from "../../a-app/provider/apiProvider.tsx";
import type { TelegramMessageListDto } from '../../g-shared/api/telegram-mesage/dto/telegramMessageListDto.ts';
import { TelegramMessagesFilters } from "../../d-widgets/telegram-message/telegramMessageFilters.tsx";
import { useAppConfig } from "../../a-app/config/appConfigContext.tsx";

const columns: GridColDef<TelegramMessageListDto>[] = [
    { field: 'chatId', headerName: 'Chat ID', width: 150 },
    { field: 'fromId', headerName: 'Sender ID', width: 150 },
    { field: 'fromBot', headerName: 'Sender is bot', width: 150, type: 'boolean' },
    { field: 'messageId', headerName: 'Message ID', width: 150 },
    { field: 'type', headerName: 'Type', width: 150 },
    { field: 'step', headerName: 'Step', width: 150 },
    { field: 'stepContainerType', headerName: 'Container type', width: 150 },
    { field: 'text', headerName: 'Text', width: 150 },
    {
        field: 'createdAt',
        headerName: 'Created at',
        width: 180,
        valueGetter: v => new Date(v).toLocaleString(),
        sortable: true
    },
];

export function TelegramMessagesPage() {
    const { telegramMessages } = useApi();
    const { defaultPageSize } = useAppConfig();

    const [ rows, setRows ] = useState<TelegramMessageListDto[]>([]);
    const [ rowCount, setRowCount ] = useState(0);
    const [ loading, setLoading ] = useState(false);
    const [ error, setError ] = useState<string | null>(null);

    const [ paginationModel, setPaginationModel ] = useState<GridPaginationModel>({
        page: 0,
        pageSize: defaultPageSize,
    });

    const [ sortModel, setSortModel ] = useState<GridSortModel>([ { field: "createdAt", sort: "desc" } ]);

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
    }, [ paginationModel, sortModel ]);

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
                    setPaginationModel({ page: 0, pageSize: defaultPageSize });
                }}
            />
            <Box>
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