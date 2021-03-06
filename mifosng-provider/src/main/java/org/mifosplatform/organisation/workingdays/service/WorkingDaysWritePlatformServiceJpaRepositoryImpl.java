/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.workingdays.service;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.configuration.domain.ConfigurationDomainService;
import org.mifosplatform.organisation.workingdays.domain.WorkingDays;
import org.mifosplatform.organisation.workingdays.domain.WorkingDaysRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingDaysWritePlatformServiceJpaRepositoryImpl implements WorkingDaysWritePlatformService {

    private final WorkingDaysRepositoryWrapper daysRepositoryWrapper;
    private final ConfigurationDomainService configurationDomainService;

    @Autowired
    public WorkingDaysWritePlatformServiceJpaRepositoryImpl(final WorkingDaysRepositoryWrapper daysRepositoryWrapper,
            final ConfigurationDomainService configurationDomainService) {
        this.daysRepositoryWrapper = daysRepositoryWrapper;
        this.configurationDomainService = configurationDomainService;
    }

    @Override
    public boolean isWorkingDay(LocalDate transactionDate) {
        final WorkingDays workingDays = this.daysRepositoryWrapper.findOne();
        return WorkingDaysUtil.isWorkingDay(workingDays, transactionDate);
    }

    @Override
    public boolean isTransactionAllowedOnNonWorkingDay() {
        return this.configurationDomainService.allowTransactionsOnNonWorkingDayEnabled();
    }

}
