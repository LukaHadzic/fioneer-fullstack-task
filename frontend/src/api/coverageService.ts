import {api} from "./client";

export const coverageService = {

    delete: async (ids: number[]): Promise<string> => {
        const response = await api.delete(`/coverages/delete`, { data: ids });
        return response.data;
    },
}