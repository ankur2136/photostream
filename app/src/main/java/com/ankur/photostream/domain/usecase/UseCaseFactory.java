package com.ankur.photostream.domain.usecase;


import com.ankur.photostream.data.repository.RepositoryFactory;
import com.ankur.photostream.domain.interactor.GetItemsUseCase;
import com.ankur.photostream.executor.ExecutorFactory;

public class UseCaseFactory {

    public static GetItemsUseCase newGetPhotoItemUseCaseInstance() {
        return new GetPhotoItemsUseCase(RepositoryFactory.getPhotosRepositoryInstance(),
                ExecutorFactory.getThreadExecutorInstance());
    }
}
