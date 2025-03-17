import { ActionReducerMapBuilder, createAsyncThunk } from "@reduxjs/toolkit";
import { apiBase, size } from "./properties";



export const socialApi = {
    findAll:createAsyncThunk(
        'extraReducer/findall',
        async ({token, page}:{token:string, page:number}):Promise<ImagenInterface[]>=>{
            try {
                const ft = await fetch(`${apiBase}/image?page=${page}&size=${size}`,{
                    method:'GET',
                    headers:{
                        'Authorization':`Bearer ${token}`
                    }
                });
                if(!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const err = error as ErrorDto;
                throw {message:err.message};
            }
        }
    ),
    saveNewImage:createAsyncThunk(
        'extraReducer/saveimage',
        async ({token, formdata}:{token:string, formdata:FormData})=>{
            try {
                const ft = await fetch(`${apiBase}/image`,{
                    method:'POST',
                    headers:{
                        'Authorization':`Bearer ${token}`
                    },
                    body:formdata
                });
                if(!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const err = error as ErrorDto;
                throw {message:err.message};
            }
        }
    )
}



export function generateSocialExtraReducer(builder:ActionReducerMapBuilder<SocialInitialState>){
    builder.addCase(socialApi.findAll.fulfilled, (state, action)=>{
        state.images = action.payload;
        state.message = '';
    });
    builder.addCase(socialApi.findAll.rejected, (state)=>{
        state.images = [];
    });

    builder.addCase(socialApi.saveNewImage.fulfilled, (state, action)=>{
        state.images = [action.payload, ...state.images];
    });
    builder.addCase(socialApi.saveNewImage.rejected, (state, action)=>{
        const message = action.error.message;
        state.message = message?message:'Error al enviar publicacion';
    });
}