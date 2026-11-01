import {Accumulation} from "../types";
import {useEffect, useMemo, useState} from "react";
import {accumulationService} from "../api/accumulationService";
import {
    AnalyticalTable,
    BusyIndicator,
    Button,
    MessageStrip,
    Tag
} from "@ui5/webcomponents-react";
import {useNavigate} from "react-router-dom";

export const AccumulationsTablePage: React.FC = () => {
    const [accumulations, setAccumulations] = useState<Accumulation[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    const fetchAccumulations = async () => {
        try{
            setLoading(true);
            setError(null);
            const data = await accumulationService.getAll();
            setAccumulations(data);
        } catch(error: any) {
            setError(error.response?.data?.message || 'Error while loading accumulations');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchAccumulations();
    }, []);

    const navigate = useNavigate();

    const columns = useMemo(
        () => [
            {
                Header: 'ID',
                accessor: 'id',
            },
            {
                Header: 'Name',
                accessor: 'name',
            },
            {
                Header: 'Class of Business',
                accessor: 'business',
            },
            {
                Header: 'Status',
                accessor: 'status',
                Cell: (instance: any) => {
                    const status = instance.cell.value;
                    const statusState = status === 'ACTIVE' ? 'Positive' : status === 'DRAFT' ? 'Information' : 'Critical';
                    return <Tag design={statusState}>{status}</Tag>;
                },
            },
            {
                Header: 'Description',
                accessor: 'description',
            },
            {
                Header: 'Actions',
                id: 'actions',
                Cell: (instance: any) => {
                    const acc = instance.row.original;
                    return (
                        <div style={{ display: 'flex', gap: '5px' }}>
                            <Button design="Transparent" onClick={() => navigate(`/accumulations/${acc.id}`)}>View Details</Button>
                            <Button design="Transparent" onClick={() => console.log('Edit', acc.id)}>Edit</Button>
                        </div>
                    );
                },
            },
        ],
        []
    );

    return (
        <div style={{ padding: '20px' }}>
            <h2>Accumulations</h2>

            {error && (
                <MessageStrip design="Negative" style={{ marginBottom: '20px' }}>
                    {error}
                </MessageStrip>
            )}

            <BusyIndicator active={loading} delay={200}>
                <div>
                    {!loading && !error && accumulations.length === 0 ? (
                        <div style={{ textAlign: 'center', padding: '40px', color: '#gnc345' }}>
                            <p>No accumulations found</p>
                            <Button design="Emphasized" onClick={fetchAccumulations}>Reload</Button>
                        </div>
                    ) : (
                        <AnalyticalTable
                            data={accumulations}
                            columns={columns}
                            visibleRows={Math.min(Math.max(accumulations.length, 1), 10)}
                            // subTableLoading={loading}

                        />
                    )}
                </div>
            </BusyIndicator>
        </div>
    );
};