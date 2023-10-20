import {
    CellContext,
    createColumnHelper,
    flexRender,
    getCoreRowModel,
    useReactTable,
} from '@tanstack/react-table';
import Badge from 'components/Badge/Badge';
import {
    JobBasicModel,
    WorkflowExecutionModel,
} from 'middleware/automation/project';
import useWorkflowExecutionDetailsDialogStore from 'pages/automation/project/stores/useWorkflowExecutionDetailsDialogStore';

const getDuration = (
    info: CellContext<WorkflowExecutionModel, JobBasicModel | undefined>
) => {
    const infoValue = info.getValue();

    const startDate = infoValue?.startDate?.getTime();
    const endDate = infoValue?.endDate?.getTime();

    if (startDate && endDate) {
        return `${Math.round(endDate - startDate)}ms`;
    }
};

const columnHelper = createColumnHelper<WorkflowExecutionModel>();

const columns = [
    columnHelper.accessor((row) => row.job, {
        header: 'Status',
        cell: (info) => (
            <Badge
                color={
                    info.getValue()?.status === 'COMPLETED'
                        ? 'green'
                        : info.getValue()?.status === 'FAILED'
                        ? 'red'
                        : 'default'
                }
                text={info.getValue()?.status ?? ''}
            />
        ),
    }),
    columnHelper.accessor('workflow', {
        header: 'Workflow',
        cell: (info) => info.getValue()?.label,
    }),
    columnHelper.accessor('project', {
        header: 'Project',
        cell: (info) => info.getValue()?.name,
    }),
    columnHelper.accessor('instance', {
        header: 'Instance',
        cell: (info) => info.getValue()?.name,
    }),
    columnHelper.accessor((row) => row.job, {
        header: 'Duration',
        cell: (info) => getDuration(info),
    }),
    columnHelper.accessor((row) => row.job, {
        header: 'Execution date',
        cell: (info) => (
            <>
                {info.getValue()?.startDate &&
                    `${info.getValue()?.startDate?.toLocaleDateString()} ${info
                        .getValue()
                        ?.startDate?.toLocaleTimeString()}`}
            </>
        ),
    }),
];

const WorkflowExecutionsTable = ({data}: {data: WorkflowExecutionModel[]}) => {
    const reactTable = useReactTable<WorkflowExecutionModel>({
        data,
        columns,
        getCoreRowModel: getCoreRowModel(),
    });

    const {setWorkflowExecutionId, setWorkflowExecutionDetailsDialogOpen} =
        useWorkflowExecutionDetailsDialogStore();

    const headerGroups = reactTable.getHeaderGroups();
    const rows = reactTable.getRowModel().rows;

    const handleRowClick = (index: number) => {
        if (data[index].id) {
            setWorkflowExecutionId(data[index].id!);

            setWorkflowExecutionDetailsDialogOpen(true);
        }
    };

    return (
        <table className="w-full divide-y divide-gray-300 bg-white text-sm">
            <thead>
                {headerGroups.map((headerGroup) => (
                    <tr key={headerGroup.id}>
                        {headerGroup.headers.map((header) => (
                            <th
                                key={header.id}
                                className="sticky top-0 z-10 bg-white p-3 text-left text-xs font-medium uppercase tracking-wide text-gray-500"
                            >
                                {!header.isPlaceholder &&
                                    flexRender(
                                        header.column.columnDef.header,
                                        header.getContext()
                                    )}
                            </th>
                        ))}
                    </tr>
                ))}
            </thead>

            <tbody className="divide-y divide-gray-200 bg-white">
                {rows.map((row) => (
                    <tr
                        className="cursor-pointer"
                        key={row.id}
                        onClick={() => handleRowClick(row.index)}
                    >
                        {row.getVisibleCells().map((cell) => (
                            <td
                                key={cell.id}
                                className="whitespace-nowrap px-3 py-4 text-sm text-gray-900"
                            >
                                {flexRender(
                                    cell.column.columnDef.cell,
                                    cell.getContext()
                                )}
                            </td>
                        ))}
                    </tr>
                ))}
            </tbody>
        </table>
    );
};

export default WorkflowExecutionsTable;