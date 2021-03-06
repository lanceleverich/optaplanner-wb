/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.workbench.screens.domaineditor.backend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.backend.project.ProjectClassLoaderHelper;
import org.kie.workbench.common.services.datamodeller.core.DataObject;
import org.kie.workbench.common.services.datamodeller.core.impl.AnnotationImpl;
import org.kie.workbench.common.services.datamodeller.core.impl.DataObjectImpl;
import org.kie.workbench.common.services.datamodeller.core.impl.ImportImpl;
import org.kie.workbench.common.services.datamodeller.util.DriverUtils;
import org.kie.workbench.common.services.shared.project.KieProject;
import org.kie.workbench.common.services.shared.project.KieProjectService;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.domain.solution.AbstractSolution;
import org.optaplanner.workbench.screens.domaineditor.backend.server.validation.ScoreHolderUtils;
import org.uberfire.backend.vfs.Path;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScoreHolderUtilsTest {

    @Mock
    private KieProjectService kieProjectService;

    @Mock
    private ProjectClassLoaderHelper classLoaderHelper;

    private ScoreHolderUtils scoreHolderUtils;

    @Before
    public void setUp() {
        scoreHolderUtils = new ScoreHolderUtils(kieProjectService,
                                                classLoaderHelper);
    }

    @Test
    public void extractScoreTypeFqn() {
        DataObject dataObject = new DataObjectImpl("test",
                                                   "Test");
        dataObject.addAnnotation(new AnnotationImpl(DriverUtils.buildAnnotationDefinition(PlanningSolution.class)));
        dataObject.addProperty("score", HardSoftScore.class.getName());

        String result = scoreHolderUtils.extractScoreTypeFqn(dataObject);

        assertEquals(HardSoftScore.class.getName(),
                     result);
    }
}
