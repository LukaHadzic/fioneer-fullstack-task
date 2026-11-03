import {Accumulation} from "../types";
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

    getAll: async (): Promise<Accumulation[]> => {
        const response = await api.get('/accumulations');
        return response.data;
    },

    create: async (data: Accumulation): Promise<Accumulation> => {
        const response = await api.post('/accumulations/create', data);
        return response.data;
    },

    // delete: async (ids: number[]): Promise<string> => {
    //     const response = await api.delete(`/accumulations`, { data: ids });
    //     return response.data;
    // },

}