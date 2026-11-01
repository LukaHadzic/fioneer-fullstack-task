import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useMemo, useState} from "react";
import {Accumulation} from "../types";
import {accumulationService} from "../api/accumulationService";
import {
    AnalyticalTable,
    BusyIndicator,
    Button,
    DynamicPage, DynamicPageHeader,
    DynamicPageTitle,
    FlexBox, FlexBoxDirection, FlexBoxWrap, Label, MessageBox, MessageBoxAction,
    MessageStrip,
    Tag, Text,
    Title, Toolbar, ToolbarSpacer
} from "@ui5/webcomponents-react";

export const AccumulationsDetailsDynamic: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();

    const [accumulation, setAccumulation] = useState<Accumulation | null>(null);
    const [selectedCoverageIds, setSelectedCoverageIds] = useState<number[]>([]);
    const [confirmOpen, setConfirmOpen] = useState(false);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    const handleRowSelect = (e: any) => {
        const { selectedRowIds, rowsById } = e.detail;
        const ids = Object.keys(selectedRowIds ?? {})
            .map(rowId => rowsById[rowId]?.original?.id)
            .filter((v): v is number => v != null);
        setSelectedCoverageIds(ids);
    };

    const handleBatchDelete = async () => {
        try {
            // await accumulationService.deleteCoverages(Number(id), selectedCoverageIds);
            setAccumulation(prev =>
            prev ? {... prev, coverages: prev.coverages?.filter(c => !selectedCoverageIds.includes(c.id))}
            : prev);
            setSelectedCoverageIds([]);
        } catch (err) {
            console.error(err);
            setError("Error deleting coverages.");
        } finally {
            setConfirmOpen(false);
        }
    }

    useEffect(() => {
        const fetchAccumulation = async () => {
            if (!id) return;

            try {
                setLoading(true);
                setError(null);
                const numericId = Number(id);
                const data = await accumulationService.getById(numericId);
                setAccumulation(data);
            } catch (error) {
                setError("Error fetching accumulation details.");
            } finally {
                setLoading(false);
            }
        };

        fetchAccumulation();
    }, [id]);

    const coverageColumns = useMemo(() => [
        {
            Header: 'Coverage ID',
            accessor: 'id',
        },
        {
            Header: 'Coverage Name',
            accessor: 'name',
        },
        {
            Header: 'Sum Insured',
            accessor: 'sumInsured',
            Cell: (instance: any) => {
                const amount = instance.cell.value;
                return <span>{amount != null ? `${amount.toLocaleString()} EUR` : 'N/A'}</span>
            },
        },
        {
            Header: 'Actions',
            Cell: (instance: any) => {
                const coverage = instance.row.original;
                return (
                    <div style={{display: 'flex', gap: '5px'}}>
                        <Button design={"Transparent"}
                                onClick={() => navigate(`coverages/${coverage.id}/edit}`)}>
                            Edit
                        </Button>
                        <Button design = "Transparent"
                                onClick={() => console.log(`coverages/${coverage.id}/delete}`)}>
                            Delete
                        </Button>
                    </div>
                );
            },
        },
    ], []);

    return (
        <BusyIndicator active={loading} delay={200} style={{ width: '100vw', height: '100vh'}}>
            {error && (
                <div style={{ padding: '20px'}}>
                    <MessageStrip design="Negative" onClose={() => setError(null)}>{error}</MessageStrip>
                </div>
            )}

            {accumulation && (
                <DynamicPage titleArea={
                    <DynamicPageTitle
                        heading={<Title>{`ID: ${accumulation.id} - ${accumulation.name}`}</Title>}
                        subheading={
                            <Tag design={accumulation.status === 'ACTIVE' ?
                            'Positive' : accumulation.status === 'PENDING' ?
                            'Information' : 'Critical'}>
                                {accumulation.status}
                            </Tag>}
                        actionsBar={
                            <FlexBox>
                                <Button design={"Transparent"}
                                        onClick={() => navigate(`/accumulations/${id}/edit`)}>
                                    Edit Accumulation
                                </Button>
                                <Button design={"Transparent"}
                                        onClick={() => navigate(`/accumulations/${id}/edit`)}>
                                    Delete Accumulation
                                </Button>
                            </FlexBox>
                        }></DynamicPageTitle>}
                        headerArea={
                            <DynamicPageHeader>
                                <FlexBox direction={FlexBoxDirection.Column} wrap={FlexBoxWrap.Wrap}
                                         style={{gap: '1rem'}}>
                                    <FlexBox direction={FlexBoxDirection.Column}>
                                        <Label>Class of Business</Label>
                                        <Text>{accumulation.business}</Text>
                                    </FlexBox>
                                    {accumulation.description && (
                                        <FlexBox direction={FlexBoxDirection.Column}>
                                            <Label>Description</Label>
                                            <Text>{accumulation.description}</Text>
                                        </FlexBox>
                                    )}
                                </FlexBox>
                            </DynamicPageHeader>
                        } style={{ width: '100%', height: '100%' }}>

                    {accumulation.coverages && accumulation.numCoverages > 0 ? (
                        <div>
                            <Toolbar>
                                <Title level={"H4"}>Coverages</Title>
                                <ToolbarSpacer />
                                {selectedCoverageIds.length > 0 && (
                                    <Button design={"Negative"} onClick={() => setConfirmOpen(true)}>
                                        Remove selected {selectedCoverageIds.length}
                                    </Button>
                                )}
                                <Button design={"Emphasized"}
                                        onClick={() => console.log('Add coverage.')}>
                                    Add coverage
                                </Button>
                            </Toolbar>
                            <AnalyticalTable
                                data={accumulation.coverages}
                                columns={coverageColumns}
                                selectionMode="Multiple"
                                onRowSelect={handleRowSelect}
                                visibleRows={10}
                                style={{width: '100%'}}/>
                        </div>
                    ) : (
                        <MessageStrip design="Information" hideCloseButton={true}>No coverages</MessageStrip>
                    )}
                    <MessageBox
                        open={confirmOpen}
                        type={"Confirm"}
                        actions={[MessageBoxAction.Delete, MessageBoxAction.Cancel]}
                        emphasizedAction={MessageBoxAction.Delete}
                        onClose={(action: any) => {
                            setConfirmOpen(false);
                            if (action === MessageBoxAction.Delete) {
                                handleBatchDelete();
                            }
                        }}>
                        Delete {selectedCoverageIds.length} selected coverage(s)?
                    </MessageBox>
                </DynamicPage>
            )}
        </BusyIndicator>
    );
};