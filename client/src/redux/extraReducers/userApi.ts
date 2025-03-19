import { ActionReducerMapBuilder, createAsyncThunk } from "@reduxjs/toolkit";
import { loginStorage } from "../../utils/loginStorage";
import { apiBase } from "./properties";


export const userApi = {
    login: createAsyncThunk(
        'extraReducer/login',
        async (data: LoginDto): Promise<TokenDto> => {
            try {
                const ft = await fetch(`${apiBase}/user/login`, {
                    method:'POST',
                    headers:{
                        'Content-Type':'application/json'
                    },
                    body:JSON.stringify(data)
                });
                if(!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const e = error as ErrorDto;
                throw {message:e.message}
            }
        },
    ),
    register: createAsyncThunk(
        'extraReducer/register',
        async (data: RegisterDto): Promise<TokenDto> => {
            try {
                const ft = await fetch(`${apiBase}/user/register`, {
                    method:'POST',
                    headers:{
                        'Content-Type':'application/json'
                    },
                    body:JSON.stringify(data)
                });
                if(!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const e = error as ErrorDto;
                throw {message:e.message}
            }
        },
    ),
    logout: createAsyncThunk(
        'extraReducer/logout',
        async ({token}:{token:string}): Promise<void> => {
            try {
                const ft = await fetch(`${apiBase}/user/logout`, {
                    method:'POST',
                    headers:{
                        'Authorization':`Bearer ${token}`
                    }
                });
                if(!ft.ok)
                    throw await ft.json();
                
            } catch (error) {
                const e = error as ErrorDto;
                throw {message:e.message}
            }
        },
    ),
    updateUserInfo: createAsyncThunk(
        'extraReducer/updateUserInfo',
        async ({token, data}:{token:string, data:SaveUserInfo}): Promise<UserInfo> => {
            try {
                const ft = await fetch(`${apiBase}/user/profile`, {
                    method:'POST',
                    headers:{
                        'Authorization':`Bearer ${token}`,
                        'Content-Type':'application/json'
                    },
                    body:JSON.stringify(data)
                });
                if(!ft.ok)
                    throw await ft.json();
                return ft.json();
                
            } catch (error) {
                const e = error as ErrorDto;
                throw {message:e.message}
            }
        },
    ),
    updateUserPicture: createAsyncThunk(
        'extraReducer/updateUserPicture',
        async ({token, data}:{token:string, data:FormData}): Promise<UserPictureResponse> => {
            try {
                const ft = await fetch(`${apiBase}/user/imageprofile`, {
                    method:'POST',
                    headers:{
                        'Authorization':`Bearer ${token}`,
                    },
                    body:data
                });
                if(!ft.ok)
                    throw await ft.json();
                return ft.json();
                
            } catch (error) {
                const e = error as ErrorDto;
                throw {message:e.message}
            }
        },
    ),
    mainUserInfo: createAsyncThunk(
        'extraReducer/mainUserInfo',
        async ({token}:{token:string}): Promise<UserInfo> => {
            try {
                const ft = await fetch(`${apiBase}/user/userinfo`, {
                    method:'GET',
                    headers:{
                        'Authorization':`Bearer ${token}`,
                    }
                });
                if(!ft.ok)
                    throw await ft.json();
                return ft.json();
                
            } catch (error) {
                const e = error as ErrorDto;
                throw {message:e.message}
            }
        },
    ),
    mainUserHeader: createAsyncThunk(
        'extraReducer/mainUserHeader',
        async ({token}:{token:string}): Promise<UserHeader> => {
            try {
                const ft = await fetch(`${apiBase}/user/headeruser`, {
                    method:'GET',
                    headers:{
                        'Authorization':`Bearer ${token}`,
                    }
                });
                if(ft.status == 403)
                    throw {message:'logout'}
                if(!ft.ok)
                    throw await ft.json();
                return ft.json();
                
            } catch (error) {
                const e = error as ErrorDto;
                throw {message:e.message}
            }
        },
    )
}


export function generateUserExtraReducer(builder:ActionReducerMapBuilder<UserInitialState>){
    builder.addCase(userApi.login.fulfilled, (state, action)=>{
        state.token = action.payload.token;
        loginStorage.save(action.payload.token);
        state.message = '';
    });
    builder.addCase(userApi.login.rejected, (state, action)=>{
        state.message = action.error.message as string;
    });

    builder.addCase(userApi.register.fulfilled, (state, action)=>{
        state.token = action.payload.token;
        state.message = '';
        loginStorage.save(action.payload.token);
    });
    builder.addCase(userApi.register.rejected, (state, action)=>{
        state.message = action.error.message as string;
    });

    builder.addCase(userApi.logout.fulfilled, state =>{
        state.token = '';
        loginStorage.save('');
    });
    builder.addCase(userApi.logout.rejected, (_state, action)=>{
        alert(action.error.message);
    });

    builder.addCase(userApi.mainUserHeader.fulfilled, (state, action)=>{
        state.userHeader = action.payload;
    });
    builder.addCase(userApi.mainUserHeader.rejected, (state, action)=>{
        if(action.error.message == 'logout'){
            state.token = '';
            loginStorage.save('');
        }
    });

    builder.addCase(userApi.mainUserInfo.fulfilled, (state, aciton)=>{
        state.userInfo = aciton.payload;
    });
}