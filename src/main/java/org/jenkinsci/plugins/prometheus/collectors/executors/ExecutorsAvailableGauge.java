package org.jenkinsci.plugins.prometheus.collectors.executors;

import hudson.model.LoadStatistics;
import io.prometheus.client.Gauge;
import io.prometheus.client.SimpleCollector;
import org.jenkinsci.plugins.prometheus.collectors.BaseMetricCollector;
import org.jenkinsci.plugins.prometheus.collectors.CollectorType;

public class ExecutorsAvailableGauge extends BaseMetricCollector<LoadStatistics.LoadStatisticsSnapshot, Gauge> {


    protected ExecutorsAvailableGauge(String[] labelNames, String namespace, String subsystem, String namePrefix) {
        super(labelNames, namespace, subsystem, namePrefix);
    }

    @Override
    protected CollectorType getCollectorType() {
        return CollectorType.EXECUTORS_AVAILABLE_GAUGE;
    }

    @Override
    protected String getHelpText() {
        return "Executors Available";
    }

    @Override
    protected SimpleCollector.Builder<?, Gauge> getCollectorBuilder() {
        return Gauge.build();
    }

    @Override
    public void calculateMetric(LoadStatistics.LoadStatisticsSnapshot jenkinsObject, String[] labelValues) {
        if (jenkinsObject == null) {
            return;
        }
        this.collector.labels(labelValues).set(jenkinsObject.getAvailableExecutors());
    }
}
