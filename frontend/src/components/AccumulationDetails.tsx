import {Accumulation} from "../types";
import { accumulationService } from "../api/accumulationService";
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {BusyIndicator, MessageStrip, Tag, Title} from "@ui5/webcomponents-react";


// interface Props {
//     accumulationName: string;
// }

// export const AccumulationDetails: React.FC<Props> = () => {
export const AccumulationDetails: React.FC = () => {
    const { id } = useParams<{ id: string }>();

    const [accumulation, setAccumulation] = useState<Accumulation | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchAccumulation = async () => {
            if (!id) return;

            try{
                setLoading(true);
                setError(null);
                const data = await accumulationService.getById(Number(id));
                console.log(data);
                setAccumulation(data);
            } catch (error) {
                setError("Error fetching accumulation data.");
            } finally {
                setLoading(false);
            }
        };

        if (id) {
            fetchAccumulation()
        }
    }, [id]);

    // if (loading) return <div>Loading accumulation details...</div>;
    // if (error) return <div style={{ color: 'red '}}>{error}</div>;
    // if (!accumulation) return <div>No accumulation data.</div>

    return (
        <div style={{ paddingTop: '20px' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '15px', marginBottom: '20px' }}>
                {/*<Button design="Transparent" onClick={() => navigate('/accumulations')}>Back to accumulations list</Button>*/}
                <Title>Accumulation details: {accumulation?.name}</Title>
            </div>

            {error && (
                <MessageStrip design={"Negative"} style={{marginBottom: '20px'}}>
                    {error}
                </MessageStrip>
            )}

            <BusyIndicator active={loading} delay={200}>
                {accumulation && (
                    <div style={{ padding: '20px' }}>
                        <h3>Id {accumulation.id} - {accumulation.name}</h3>
                        <p><strong>Class of Business: </strong>{accumulation?.business}</p>
                        <p><strong>Description: </strong>{accumulation?.description}</p>
                        <p><strong>Status: </strong>
                            <Tag design={accumulation.status === 'ACTIVE' ? 'Positive' : accumulation.status === 'DRAFT' ?
                            'Information' : 'Critical'}>
                                {accumulation?.status}
                            </Tag>
                        </p>
                        <h4>Coverages</h4>
                        {accumulation.coverages && accumulation.coverages.length > 0 ? (
                        <ul>
                            {accumulation.coverages.map((coverage, index) => (
                                <li key={index}>
                                    {coverage.name} - Amount: {coverage.sumInsured}
                                </li>
                            ))}
                        </ul>
                        ) : (<p>No coverages associated with this accumulation.</p>)}
                    </div>
                )}
            </BusyIndicator>
        </div>
    );
};