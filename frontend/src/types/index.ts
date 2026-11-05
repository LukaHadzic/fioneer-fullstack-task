export interface Coverage {
    id: number
    name: string;
    sumInsured: number;
    status: string;
    accumulation: string;
}

export interface Accumulation {
    id?: number
    name: string;
    numCoverages?: number;
    business: string;
    status: string;
    description: string;
    coverages?: Coverage[];
}

export interface DashboardStats {
    averageSumInsuredPerStatusDto: { status: string; averageInsured: number }[];
    accumulationsCreatedPerDateDto: { dateOfCreation: string; numberCreated: number }[];
}