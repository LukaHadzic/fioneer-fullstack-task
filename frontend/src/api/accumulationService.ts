import {Accumulation, DashboardStats} from "../types";
import {api} from "./client";

export const accumulationService = {

    async getByName(name: string): Promise<Accumulation> {
        const response = await api.get(`accumulations/${name}`);
        return response.data;
    },

    async getById(id: number): Promise<Accumulation> {
        const response = await api.get(`accumulations/${id}`);
        return response.data;
    },

    async getDashboardStats(): Promise<DashboardStats> {
        const response = await api.get(`/accumulations/summary`);
        return response.data;
    },

    getAll: async (): Promise<Accumulation[]> => {
        const response = await api.get('/accumulations');
        return response.data;
    },

    create: async (data: Accumulation): Promise<Accumulation> => {
        const response = await api.post('/accumulations/create', data);
        return response.data;
    },

    delete: async (accumulationName: string): Promise<string> => {
        const response = await api.delete(`/accumulations/delete`, { data: {name: accumulationName}} );
        return response.data;
    },

}