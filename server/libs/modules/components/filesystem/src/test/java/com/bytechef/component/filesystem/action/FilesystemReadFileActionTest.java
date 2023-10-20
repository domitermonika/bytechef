
/*
 * Copyright 2021 <your company/name>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytechef.component.filesystem.action;

import com.bytechef.component.filesystem.FilesystemComponentHandlerTest;
import com.bytechef.hermes.component.Context;
import com.bytechef.hermes.component.InputParameters;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.File;
import java.io.InputStream;

import static com.bytechef.component.filesystem.constant.FilesystemConstants.FILENAME;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ivica Cardic
 */
public class FilesystemReadFileActionTest {

    @Test
    public void testExecuteReadFile() {
        InputParameters inputParameters = Mockito.mock(InputParameters.class);
        File file = getSampleFile();

        Mockito.when(inputParameters.getRequiredString(FILENAME))
            .thenReturn(file.getAbsolutePath());

        Context context = Mockito.mock(Context.class);

        FilesystemReadFileAction.executeReadFile(context, inputParameters);

        ArgumentCaptor<String> filenameArgumentCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(context)
            .storeFileContent(filenameArgumentCaptor.capture(), Mockito.any(InputStream.class));

        assertThat(filenameArgumentCaptor.getValue()).isEqualTo(file.getAbsolutePath());
    }

    private File getSampleFile() {
        return new File(FilesystemComponentHandlerTest.class
            .getClassLoader()
            .getResource("dependencies/sample.txt")
            .getFile());
    }
}